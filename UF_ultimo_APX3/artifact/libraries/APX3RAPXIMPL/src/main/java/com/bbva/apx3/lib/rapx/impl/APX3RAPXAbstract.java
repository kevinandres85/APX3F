package com.bbva.apx3.lib.rapx.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.apx3.lib.rapx.APX3RAPX;

public abstract class APX3RAPXAbstract extends AbstractLibrary implements APX3RAPX {

	private static final Logger LOGGER = LoggerFactory.getLogger(APX3RAPX.class);
	
	protected ApplicationConfigurationService applicationConfigurationService;
	
	/**
	 * @param applicationConfigurationService the applicationConfigurationService to set
	 */
	public void setApplicationConfigurationService(
			ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

	public String execute2() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
