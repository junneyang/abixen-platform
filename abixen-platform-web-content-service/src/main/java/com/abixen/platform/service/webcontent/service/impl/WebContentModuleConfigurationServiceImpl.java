/**
 * Copyright (c) 2010-present Abixen Systems. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.abixen.platform.service.webcontent.service.impl;

import com.abixen.platform.service.webcontent.form.WebContentModuleConfigForm;
import com.abixen.platform.service.webcontent.model.impl.WebContent;
import com.abixen.platform.service.webcontent.model.impl.WebContentModuleConfig;
import com.abixen.platform.service.webcontent.repository.WebContentModuleConfigRepository;
import com.abixen.platform.service.webcontent.service.WebContentModuleConfigurationService;
import com.abixen.platform.service.webcontent.service.WebContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebContentModuleConfigurationServiceImpl implements WebContentModuleConfigurationService {

    private final WebContentModuleConfigRepository webContentModuleConfigRepository;
    private final WebContentService webContentService;

    @Autowired
    public WebContentModuleConfigurationServiceImpl(WebContentModuleConfigRepository webContentModuleConfigRepository,
                                                    WebContentService webContentService) {
        this.webContentModuleConfigRepository = webContentModuleConfigRepository;
        this.webContentService = webContentService;
    }

    @Override
    public WebContentModuleConfig findWebContentModuleConfig(Long moduleId) {
        return webContentModuleConfigRepository.findByModuleId(moduleId);
    }

    @Override
    public WebContentModuleConfig createWebContentModuleConfig(WebContentModuleConfigForm webContentModuleConfigForm) {
        WebContentModuleConfig webContentModuleConfig = new WebContentModuleConfig();
        webContentModuleConfig.setModuleId(webContentModuleConfigForm.getModuleId());
        WebContent webContent = webContentService.findWebContent(webContentModuleConfigForm.getContentId());
        webContentModuleConfig.setWebContent(webContent);
        return webContentModuleConfigRepository.save(webContentModuleConfig);
    }

    @Override
    public WebContentModuleConfig updateWebContentModuleConfig(WebContentModuleConfigForm webContentModuleConfigForm) {
        WebContentModuleConfig webContentModuleConfig = findWebContentModuleConfig(webContentModuleConfigForm.getModuleId());
        WebContent webContent = webContentService.findWebContent(webContentModuleConfigForm.getContentId());
        webContentModuleConfig.setWebContent(webContent);
        return webContentModuleConfigRepository.save(webContentModuleConfig);
    }
}