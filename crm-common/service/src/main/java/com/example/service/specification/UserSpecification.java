package com.example.service.specification;

import com.example.exception.UnsupportedParameterException;
import com.example.model.user.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {

    public Specification<User> findUserByParam(final String filter, String query) throws UnsupportedParameterException {

        if(filter.equalsIgnoreCase("email")) {

            Specification<User> userSpecification = (r, cq, cb) -> cb.like(r.get(filter), "%" + query + "%");

            return userSpecification;
        }

        throw new UnsupportedParameterException("Not possible to find a user by this param");
    }
}
