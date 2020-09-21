package com.jay.calculator.calculate.commandline;

import com.jay.calculator.common.exception.ServiceException;

/**
 * @author  Jay
 * this is the line command interface. all the cmd which implement this interface will deal with command line
 * */
public interface CommandLine {

    /**
     * @param commandLine
     * @throws ServiceException
     * this is the api which will deal with command line. it will throw ServiceException
     * */
    void doCommand(String commandLine) throws ServiceException;
}
