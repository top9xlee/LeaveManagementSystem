package com.example.leavemanagementsystem.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;
import org.springframework.web.servlet.view.tiles3.*;

@Configuration
public class TilesConfig {


    @Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();

        // TilesView 3
        viewResolver.setViewClass(TilesView.class);

        return viewResolver;
    }

    @Bean(name = "tilesConfigurer")
    public TilesConfigurer getTilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();

        // TilesView 3
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");

        return tilesConfigurer;
    }


}