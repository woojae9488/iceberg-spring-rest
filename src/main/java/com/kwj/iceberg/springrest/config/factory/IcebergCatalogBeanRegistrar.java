package com.kwj.iceberg.springrest.config.factory;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import com.kwj.iceberg.springrest.config.property.IcebergCatalogsProperties;

public class IcebergCatalogBeanRegistrar implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		IcebergCatalogsProperties properties = beanFactory.getBean(IcebergCatalogsProperties.class);
		// TODO implements
		throw new NotImplementedException();
	}

	private void createIcebergCatalogBean(ConfigurableListableBeanFactory beanFactory,
		String name, IcebergCatalogsProperties.IcebergCatalogProperties<?> catalogProperties) {
		// TODO implements
		throw new NotImplementedException();
	}

}
