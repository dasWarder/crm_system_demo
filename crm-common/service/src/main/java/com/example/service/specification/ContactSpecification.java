package com.example.service.specification;

import com.example.Contact;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ContactSpecification {

    public static Specification<Contact> findContactByFullName(final String firstName, final String lastName) {

        return (root, criteriaQuery, criteriaBuilder) -> {

            Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"), firstName + "%");
            Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"), lastName + "%");
            Predicate reversedSearchFirstName = criteriaBuilder.like(root.get("firstName"), lastName + "%");
            Predicate reversedSearchLastName = criteriaBuilder.like(root.get("lastName"), firstName + "%");

            Predicate reversedOrderPredicate = criteriaBuilder.and(reversedSearchFirstName, reversedSearchLastName);
            Predicate commonOrderPredicate = criteriaBuilder.and(firstNamePredicate, lastNamePredicate);

            Predicate resultPredicate = criteriaBuilder.or(reversedOrderPredicate, commonOrderPredicate);

            return resultPredicate;
        };
    }
}
