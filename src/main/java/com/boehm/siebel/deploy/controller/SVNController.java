package com.boehm.siebel.deploy.controller;

import com.boehm.siebel.deploy.deploy.AdmDTO;
import com.boehm.siebel.deploy.deploy.DeploymentDAO;
import com.boehm.siebel.deploy.deploy.DeploymentException;
import com.boehm.siebel.deploy.deploy.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"environment", "user"})
@RequestMapping("/svn")
public class SVNController {
    @Autowired
    DeploymentService deplService;
    @Autowired
    DeploymentDAO deplDao;

    @GetMapping("adm")
    public String importAdm(){
        return "adm";
    }

    @RequestMapping(value = "/api/importAdm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String convert(@RequestBody String folder) {
        String result;

        try {
            deplService.importAdmFile(deplDao.getAdmFiles(folder));
            result = "Ok";
        } catch (DeploymentException ex){
            result = "Error";
        }
        return result;
    }
}