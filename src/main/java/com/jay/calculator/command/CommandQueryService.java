package com.jay.calculator.command;

import com.jay.calculator.common.exception.ServiceException;

/**
 * @author Jay
 * this is the interface for query. as there are no inquiry api in executor this one will help other component do some inquiry things.
 */
public interface CommandQueryService {
    /**
     * query stack
     * @return Stack</ String>
     */
    String queryStack() throws ServiceException;

}
