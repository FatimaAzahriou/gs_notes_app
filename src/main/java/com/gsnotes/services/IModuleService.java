package com.gsnotes.services;

import com.gsnotes.bo.Modul;

import java.util.List;

public interface IModuleService {
    public void addModule(Modul module);

    public void updateModule(Modul module);

    public List<Modul> getAllModules();

    public void deleteModule(Long id);

    public Modul getModuleById(Long id);
}
