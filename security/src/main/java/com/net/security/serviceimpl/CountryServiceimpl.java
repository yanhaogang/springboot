package com.net.security.serviceimpl;

import com.net.security.bean.Country;
import com.net.security.mapper.CountryMapper;
import com.net.security.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int getidbyname(String name) {
        return countryMapper.getIdbyName(name);
    }

    @Override
    public Boolean deletebyid(int id) {
        return countryMapper.deletebyid(id);
    }
}
