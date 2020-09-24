import com.jay.calculator.calculate.CalculatorFacade;
import com.jay.calculator.calculate.CalculatorFacadeImpl;
import com.jay.calculator.command.CommandQueryService;
import com.jay.calculator.command.CommandQueryServiceImpl;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.BeanFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ServiceException, IOException {
        //init context here
        BeanFactory.initBean();
        System.out.println("context is:" + ApplicationContext.getContext());
        //get query service
        CommandQueryService commandQueryService = (CommandQueryService) ApplicationContext.getContext().get(CommandQueryServiceImpl.class);
        //read input
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String errorInfo = null;
            String str = sc.nextLine();
            CalculatorFacade calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
            try {
                // call service here
                calculatorFacade.processCommand(str);
            } catch (ServiceException e) {
                errorInfo = e.getMessage();
            } finally {
                //[requirement] to meet the requirement
                // 1. print stack once a line is operated
                // 2. print stack after warning displayed
                errorInfo = errorInfo == null ? "" : errorInfo + "\n";
                String stackInfo = "stack:" + commandQueryService.queryStack();
                String rstMsg = errorInfo + stackInfo;
                System.out.println(rstMsg);
            }


        }
    }
}
