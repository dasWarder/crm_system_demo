package com.example.service.specification;

import com.example.Contact;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ContactSpecification {

    public static Specification<Contact> findContactsByFullName(final String firstName, final String lastName) {

        return (r, cq, cb) -> {

            Predicate firstNamePredicate = cb.like(r.get("firstName"), firstName + "%");
            Predicate lastNamePredicate = cb.like(r.get("lastName"), lastName + "%");
            Predicate reversedSearchFirstName = cb.like(r.get("firstName"), lastName + "%");
            Predicate reversedSearchLastName = cb.like(r.get("lastName"), firstName + "%");

            Predicate reversedOrderPredicate = cb.and(reversedSearchFirstName, reversedSearchLastName);
            Predicate commonOrderPredicate = cb.and(firstNamePredicate, lastNamePredicate);

            Predicate resultPredicate = cb.or(reversedOrderPredicate, commonOrderPredicate);

            return resultPredicate;
        };
    }

    public static Specification<Contact> findContactsByParam(final String filteredBy, String query) {

        return (r, cq, cb) -> cb.like(r.get(filteredBy), "%" + query + "%");
    }
}
