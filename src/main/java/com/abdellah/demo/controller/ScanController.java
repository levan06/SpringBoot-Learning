package com.abdellah.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdellah.demo.dto.ScanRequest;
import com.abdellah.demo.dto.ScanResponse;
import com.abdellah.demo.service.ScanService;

@RestController
public class ScanController 
{
    private ScanService scanService;

    public ScanController( ScanService scanService )
    {
        this.scanService = scanService;
    }
    @PostMapping("/scan")
    public ScanResponse scan( @RequestBody ScanRequest request )
    {
        return scanService.scan( request.getTarget() );
    }
}
