# Change Application Context Path

To publish the application at a location other than `/casa`, do the following:

1. Log into the Gluu Server chroot (e.g. `# gluu-serverd login`)       
      
1. Edit tag `<Set name="contextPath">` in file `/opt/gluu/jetty/casa/webapps/casa.xml` with the new path you want to use. For example, if you chose `/creds`, you would do the following:    
   
    ```  
      <Set name="contextPath">/creds</Set>     
    ```  

1. Edit tag `<Set name="contextPath">` in file `/opt/gluu/jetty/casa/webapps/casa_web_resources.xml` appropriately with the new path you want to use.
    
1. Adjust Apache's .conf file:    

    - Locate the `https_gluu.conf` file. The exact location will vary depending on your distribution. In Ubuntu, for example, you can find it at `/etc/apache2/sites-available`
   
    - Find the section starting with `<Location /casa>` and replace the 2 occurrences of `casa` with the path of your choice. Do not use trailing slashes   

    - Add the following directive: `Redirect /casa /<new-context-path>` before all `<Location>` and `<Proxy>` sections

1. Adjust custom script settings:    

    - Log in to oxTrust and go to `Configuration` > `Manage custom scripts`
   
    - On the "Person Authentication" tab, collapse the script labeled `casa` and adjust the "supergluu_app_id" property accordingly. Skip this step if your Casa installation has already been used for enrolling or authenticating via Super Gluu.
   
    - At the bottom, press the "Update" button

1. Wait for around 1 minute (so the server picks the script changes), then restart Casa and Apache services. Use this [page](https://gluu.org/docs/gluu-server/4.4/operation/services/#restart) as a guide

1. The application should be accessible now at the new URL.
