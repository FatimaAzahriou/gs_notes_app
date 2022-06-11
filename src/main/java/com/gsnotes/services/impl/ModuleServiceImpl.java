package com.gsnotes.services.impl;

import com.gsnotes.bo.Modul;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IModuleDAO;
import com.gsnotes.services.IModuleService;
import com.gsnotes.utils.export.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModuleServiceImpl implements IModuleService {
    @Autowired
    private IModuleDAO moduleDAO;


    @Override
    public void addModule(Modul module) {
        moduleDAO.save(module);

    }


    @Override
    public void updateModule(Modul module) {
        moduleDAO.save(module);
    }

    @Override
    public List<Modul> getAllModules() {
        return moduleDAO.findAll();
    }

    @Override
    public void deleteModule(Long id) {
        moduleDAO.deleteById(id);
    }

    @Override
    public Modul getModuleById(Long id) {
        return moduleDAO.getById(id);
    }
}
