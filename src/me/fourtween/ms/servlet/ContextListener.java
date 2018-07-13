package me.fourtween.ms.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import me.fourtween.ms.utils.ConnectionManager;



public class ContextListener implements ServletContextListener
{
 


    public void contextInitialized( ServletContextEvent arg0 ){
        System.out.println("\n---------------------------------------------");
        System.out.println("ContextInitialized Method has been Called......");
//        ConnectionManager.configureBoneCPConnectionPool();
        
        
        System.out.println("---------------------------------------------\n");
    }

    public void contextDestroyed( ServletContextEvent arg0 ){
        System.out.println("\n---------------------------------------------");
        System.out.println("contextDestroyed Method has been Called......");
//        ConnectionManager.shutdownBoneCPConnectionPool();
        
        
        System.out.println("---------------------------------------------\n");
    }

}
