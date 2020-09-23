package com.jay.calculator.command.cmd;

import com.jay.calculator.command.dal.DataDao;
import com.jay.calculator.command.dal.DataDaoImpl;
import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.command.model.ParamPairBean;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.Service;

import java.math.BigDecimal;

@Service()
public class CalculateCommandBase {

    public void inputData(String command) throws ServiceException {
        UndoBean undoBean = new UndoBean();
        this.inputIntoStack(undoBean, command);
    }

    protected void getParamsForCalculate(ParamPairBean paramPairBean, UndoBean undoBean) throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        try {
            String secondElement = this.getSingleParamFromStack(undoBean);
            String firstElement = this.getSingleParamFromStack(undoBean);
            BigDecimal secondNumber = new BigDecimal(secondElement);
            BigDecimal firstNumber = new BigDecimal(firstElement);
            paramPairBean.setFirstNumber(firstNumber);
            paramPairBean.setSecondNumber(secondNumber);
        } catch (ServiceException e) {
            this.recover(undoBean);
            throw e;
        }
    }

    protected boolean contextStackIsEmpty() {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        return dataDao.stackIsEmpty();
    }

    protected UndoBean getUndoBeanFromUndoStack() throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        return dataDao.popFromUndoStack();
    }

    protected void pushIntoStack(String element) throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        dataDao.pushStack(element);
    }

    protected String popFromStack() throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        return dataDao.popFromStack();
    }

    /*the method below deal with both contextStack and undoStack*/
    protected String getSingleParamFromStack(UndoBean undoBean) throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        String element = dataDao.popFromStack();
        undoBean.getResultOutStack().push(element);
        return element;
    }

    protected void setResultIn(UndoBean undoBean, String rst) throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        dataDao.pushUndoStack(undoBean);
        boolean rstExist = rst != null;
        if (rstExist) {
            undoBean.getResultInStack().push(rst);
            dataDao.pushStack(rst);
        }
    }

    protected void inputIntoStack(UndoBean undoBean, String command) throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        dataDao.pushStack(command);
        undoBean.getResultInStack().push(command);
        dataDao.pushUndoStack(undoBean);
    }

    protected void takeOutResult(UndoBean undoBean) throws ServiceException {
        while (!undoBean.getResultInStack().isEmpty()) {
            boolean hasElementInStack = !this.contextStackIsEmpty();
            if (hasElementInStack) {
                String inStr = undoBean.getResultInStack().pop();
                String rst = this.popFromStack();
                boolean undoInfoMatch = inStr.equals(rst);
                if (!undoInfoMatch) {
                    throw new ServiceException(ErrorCodeEnum.ERROR_UNDO_INFO_MISMATCH, "undo param is:[" + inStr + "],stack param is:[" + rst + "]");
                }
            }
        }
    }

    protected void recover(UndoBean undoBean) throws ServiceException {
        while (!undoBean.getResultOutStack().isEmpty()) {
            String inStr = undoBean.getResultOutStack().pop();
            this.pushIntoStack(inStr);
        }
    }
}
