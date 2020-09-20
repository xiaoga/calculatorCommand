package com.jay.calculator.service.command.cmd;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.dal.DataDao;
import com.jay.calculator.service.command.dal.DataDaoImpl;
import com.jay.calculator.service.command.dal.UndoBean;
import com.jay.calculator.service.command.model.ParamPairBean;
import com.jay.calculator.service.exception.ErrorCodeEnum;
import com.jay.calculator.service.exception.ServiceException;

import java.math.BigDecimal;

public class CalculateCommandBase {

    public void inputData(String command) throws ServiceException {
        UndoBean undoBean = new UndoBean();
        this.inputIntoStack(undoBean, command);
    }

    protected void getParamsForCalculate(ParamPairBean paramPairBean, UndoBean undoBean) throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);

        String secondElement = this.getSingleParamFromStack(undoBean);
        String firstElement = this.getSingleParamFromStack(undoBean);
        BigDecimal secondNumber = this.parseElementToBigDecimal(secondElement);
        BigDecimal firstNumber = this.parseElementToBigDecimal(firstElement);
        paramPairBean.setFirstNumber(firstNumber);
        paramPairBean.setSecondNumber(secondNumber);

    }

    protected boolean contextStackIsEmpty() {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        return dataDao.stackIsEmpty();
    }

    protected UndoBean getUndoBeanFromUndoStack() throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        return dataDao.popFromUndoStack();
    }

    protected void pushIntoStack(String element) {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        dataDao.pushStack(element);
    }

    protected String popFromStack() throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        return dataDao.popFromStack();
    }

    private BigDecimal parseElementToBigDecimal(String element) throws ServiceException {
        try {
            BigDecimal number = new BigDecimal(element);
            return number;
        } catch (Exception exception) {
            throw new ServiceException(ErrorCodeEnum.ERROR_PARAM_IS_NOT_NUMBER, "number format exception, element is:[" + element + "]", exception);
        }
    }

    /*the method below deal with both contextStack and undoStack*/
    protected String getSingleParamFromStack(UndoBean undoBean) throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        String element = (String) dataDao.popFromStack();
        undoBean.getResultOutStack().push(element);
        return element;
    }

    protected void setResultIn(UndoBean undoBean, String rst) {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        dataDao.pushUndoStack(undoBean);
        boolean rstExist = rst != null;
        if (rstExist) {
            undoBean.getResultInStack().push(rst);
            dataDao.pushStack(rst.toString());
        }
    }

    protected void inputIntoStack(UndoBean undoBean, String command) {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        dataDao.pushStack(command);
        undoBean.getResultInStack().push(command);
        dataDao.pushUndoStack(undoBean);
    }


}
