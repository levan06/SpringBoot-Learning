package com.abdellah.demo.dto;

public class ScanResponse 
{
    private String target;
    private String ipv4;
    private String nmap;

    public ScanResponse( String target, String ipv4, String nmap )
    {
        this.target = target;
        this.ipv4   = ipv4;
        this.nmap   = nmap;
    }

    public String getTarget()
    {
        return this.target;
    }

    public String getIPv4()
    {
        return this.ipv4;
    }

    public String getNmap()
    {
        return this.nmap;
    }
}
