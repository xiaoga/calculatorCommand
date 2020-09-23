package com.jay.calculator.calculate.commandline;

import com.jay.calculator.common.exception.ManInfo;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.Service;

import java.util.Map;
import java.util.Set;

@Service()
@ManInfo(usage = "commandLine:'man'. show system commandline and command")
public class CommandLineMan implements CommandLine {
    @Override
    public void doCommand(String commandLine) {
        Map<Class<?>, Object> context = ApplicationContext.getContext();
        Set<Class<?>> keys = context.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Class<?> key : keys) {
            ManInfo manInfo = key.getAnnotation(ManInfo.class);
            if (manInfo != null)
                stringBuilder.append(manInfo.usage() + "\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
