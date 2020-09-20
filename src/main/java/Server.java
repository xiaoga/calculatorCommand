import com.jay.calculator.ApplicationContext;
import com.jay.calculator.facade.CalculatorFacade;
import com.jay.calculator.facade.CalculatorFacadeImpl;
import com.jay.calculator.service.command.CommandExecutor;
import com.jay.calculator.service.command.CommandExecutorImpl;
import com.jay.calculator.service.command.CommandQueryService;
import com.jay.calculator.service.command.CommandQueryServiceImpl;
import com.jay.calculator.service.exception.ServiceException;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ServiceException {
        //init context here
        ApplicationContext.initContext();
        //read input and call service here
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            //System.out.println("["+str+"]");
            CalculatorFacade calculatorFacade=(CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
            calculatorFacade.processCommand(str);

            CommandQueryService commandQueryService=(CommandQueryService) ApplicationContext.getContext().get(CommandQueryServiceImpl.class);
            System.out.println("stack:"+commandQueryService.queryStack());

        }
    }
}
