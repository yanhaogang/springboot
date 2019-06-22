package com.net.security.serviceimpl;

import com.net.security.bean.Country;
import com.net.security.mapper.CountryMapper;
import com.net.security.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CountryServiceimpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;


    @Override
    public List<Country> getAll() {
        return countryMapper.getAll();
    }

    @Override
    public List<String> getAllcontinent() {
        return countryMapper.getAllcontinent();
    }


    @Override
    public List<String> getCountriesbyContinent(String continent) {
        return countryMapper.getCountriesbycontinent(continent);
    }

    @Override
    public String getContinentbycontry(String name) {
        return countryMapper.getContinentbycountry(name);
    }

    @Override
    public List<String> getallcountry() {
        return countryMapper.getallcountry();
    }

    @Override
    public String getnamebyid(int id) {
        return countryMapper.getNamebyId(id);
    }

    @Override
    public String getNickByname(String name) {
        return countryMapper.getNickByname(name);
    }

    @Override
    public int getidbyname(String name) {
        return countryMapper.getIdbyName(name);
    }

    @Override
    public List<Country> getPartBylike(String arg0, int arg1, int arg2) {
        return countryMapper.getPartBylike(arg0,arg1,arg2);
    }

    @Override
    public Integer getNumBysearch(String _parameter) {
        return countryMapper.getNumBysearch(_parameter);
    }

    @Override
    public Integer getNum() {
        return countryMapper.getNum();
    }

    @Override
    public List<Country> getPart(int arg0, int arg1) {
        return countryMapper.getPart(arg0,arg1);
    }

    @Override
    public List<Country> getAllBylike(String _parameter) {
        return countryMapper.getAllBylike(_parameter);
    }

    @Override
    public Boolean deletebyid(int id) {
        return countryMapper.deletebyid(id);
    }

    @Override
    public void insert(Country country) {
        countryMapper.insert(country);
    }

    @Override
    public void update(String arg0, String arg1, Date arg2, String arg3, String arg4, Float arg5, Float arg6, String arg7, String arg8, String arg9, int arg10) {
        countryMapper.update(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    }
}
