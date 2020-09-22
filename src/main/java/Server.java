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
        //ApplicationContext.initContext();
        BeanFactory.initBean();
        System.out.println("context is:"+ApplicationContext.getContext());
        //get query service
        CommandQueryService commandQueryService = (CommandQueryService) ApplicationContext.getContext().get(CommandQueryServiceImpl.class);
        //read input and call service here
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            CalculatorFacade calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
            try {
                calculatorFacade.processCommand(str);
            } catch (ServiceException e) {
                e.printStackTrace();
            }finally {
                //[requirement] to meet the requirement
                // 1. print stack once a line is operated
                // 2. print stack after warning displayed
                System.out.println("stack:" + commandQueryService.queryStack());
            }



        }
    }
}
