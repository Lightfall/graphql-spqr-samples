package io.leangen.graphql.samples.repo;

import io.leangen.graphql.samples.datasource.GeneralDataSource;
import io.leangen.graphql.samples.entity.School;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SchoolRepo {
    public void addSchool(School school){
        GeneralDataSource.getSchools().add(school);
    }

    public School getSchool(Long schoolId){
        System.out.println("getSchool(Long schoolId)");
        return getSchoolInternal(schoolId);
    }

    public List<School> getSchools(){
        System.out.println("getSchools()");
        return GeneralDataSource.getSchools();
    }

    public List<School> getSchools(List<Long> list){
        System.out.println("getSchools(List<Long> list)");
        return GeneralDataSource.getSchools().stream().filter(x->list.contains(x.getId())).collect(Collectors.toList());
    }


    private static School getSchoolInternal(Long schoolId){
        return GeneralDataSource.getSchools().stream().filter(x->x.getId().equals(schoolId)).findFirst().get();
    }
}
