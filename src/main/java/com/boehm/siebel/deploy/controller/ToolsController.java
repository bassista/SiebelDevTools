package com.boehm.siebel.deploy.controller;

import com.boehm.siebel.deploy.tools.ConverterRequestDTO;
import com.boehm.siebel.deploy.tools.ConverterResultDTO;
import com.boehm.siebel.deploy.tools.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"environment", "user"})
@RequestMapping("/tools")
public class ToolsController {
    @Autowired
    private ConverterService converter;

    @GetMapping("/converter")
    public String converter(ModelMap model) {
        return "converter";
    }

    @RequestMapping(value = "/api/convert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ConverterResultDTO convert(@RequestBody ConverterRequestDTO input) {
        if(input.getConvertType() == ConverterRequestDTO.ConvertType.TAB_TO_JIRA){
            return converter.convertTabToJira(input);
        } else if(input.getConvertType() == ConverterRequestDTO.ConvertType.ROWID_TO_SIEBELQL){
            return converter.convertIdToSiebelQL(input);
        } else {
            return new ConverterResultDTO("");
        }
    }

    @GetMapping("/linklist")
    public String linklist(ModelMap model) {
        model.addAttribute("name", "XXX");
        return "converter";
    }
}
