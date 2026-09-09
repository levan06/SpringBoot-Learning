package com.abdellah.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScanController 
{
    @PostMapping("/scan")
    public Map<String, String> scan(@RequestBody ScanResult request)
    {
        String ipv4         = "Non trouvé ou erreur";
        String resultatNmap = "Non trouvé ou erreur";

        try {
            // 1. Added "cmd.exe", "/c" so the shell 'for' command can execute
            ProcessBuilder pbIPv4 = new ProcessBuilder( 
                "cmd.exe", "/c",
                "for /f \"tokens=2 delims=[]\" %a in ('ping -n 1 -4 " + request.getTarget() + " ^| findstr \"[\"') do @echo %a"
            );

            Process processIPv4 = pbIPv4.start();

            BufferedReader readerIPv4 = new BufferedReader(
                new InputStreamReader(processIPv4.getInputStream())
            );

            String lineIPv4 = readerIPv4.readLine();
            if (lineIPv4 != null && !lineIPv4.trim().isEmpty()) {
                ipv4 = lineIPv4.trim();
            }

            // Clean up the process resources
            processIPv4.waitFor();

            // If it worked let's nmap it !
            ProcessBuilder pbNmap = new ProcessBuilder( "nmap", "-sV", "-sS", "-T4", ipv4 );

            Process processNmap = pbNmap.start();

            BufferedReader readerNmap = new BufferedReader(
                new InputStreamReader(processNmap.getInputStream())
            );

            String        lineNmap = "";
            StringBuilder allNmapLines = new StringBuilder();

            while( (lineNmap = readerNmap.readLine()) != null )
            {
                allNmapLines.append( lineNmap ).append( "\n" );
            }
            resultatNmap = allNmapLines.toString();

            processNmap.waitFor();
            
        } catch (IOException | InterruptedException e) {
            ipv4 = "Erreur système : " + e.getMessage();
            Thread.currentThread().interrupt(); // Restore interrupted state if InterruptedException happens
        }

        // 2. Changed "+ pb" to "+ ipv4" to return the actual string instead of the object
        return Map.of(
            "message", "Scan ipv4 pour " + request.getTarget() + " est : " + ipv4,
            "nmap", "Nmap : " + resultatNmap
        );
    }
}
