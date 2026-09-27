package com.abdellah.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;
import com.abdellah.demo.dto.ScanResponse;

@Service 
public class ScanService
{
    public ScanResponse scan(String target)
    {
        String ipv4         = "Non trouvé ou erreur";
        String resultatNmap = "Non trouvé ou erreur";

        try {
            // 1. Invoke WSL instead of cmd.exe. 
            // We use 'getent' or 'dig'/'getent ahosts' to cleanly resolve IPs in Linux, or a standard Linux ping.
            ProcessBuilder pbIPv4 = new ProcessBuilder( 
                "wsl.exe", "bash", "-c",
                "ping -c 1 -4 " + target + " | head -n 1 | grep -Eo '[0-9\\.]{7,15}'"
            );

            Process processIPv4 = pbIPv4.start();
            BufferedReader readerIPv4 = new BufferedReader(new InputStreamReader(processIPv4.getInputStream()));

            String lineIPv4 = readerIPv4.readLine();
            if (lineIPv4 != null && !lineIPv4.trim().isEmpty()) {
                ipv4 = lineIPv4.trim();
            }
            processIPv4.waitFor();

            // 2. Run Nmap entirely inside your WSL Linux environment
            // Ensure nmap is installed inside WSL (sudo apt install nmap)
            ProcessBuilder pbNmap = new ProcessBuilder(
                "wsl.exe", "nmap", "-sV", "-T4", ipv4 
            );

            Process processNmap = pbNmap.start();
            BufferedReader readerNmap = new BufferedReader(new InputStreamReader(processNmap.getInputStream()));

            String lineNmap = "";
            StringBuilder allNmapLines = new StringBuilder();

            while ((lineNmap = readerNmap.readLine()) != null) {
                allNmapLines.append(lineNmap).append("\n");
            }
            resultatNmap = allNmapLines.toString();
            processNmap.waitFor();
            
        } catch (IOException | InterruptedException e) {
            ipv4 = "Erreur système : " + e.getMessage();
            resultatNmap = "Erreur système : " + e.getMessage();
            Thread.currentThread().interrupt(); 
        }

        return new ScanResponse(target, ipv4, resultatNmap);
    }
}
