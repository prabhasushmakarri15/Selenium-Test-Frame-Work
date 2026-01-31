package com.testngproject.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoogerManager {
	public static Logger getlogger(Class<?> clazz) {
		return LogManager.getLogger();
	}

}
