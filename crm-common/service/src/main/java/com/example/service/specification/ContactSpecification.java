package com.example.service.specification;

import com.example.contactManager.Contact;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;


@Component
public class ContactSpecification {

    public Specification<Contact> findContactsByFullName(final String firstName, final String lastName) {

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

    public Specification<Contact> findContactsByParam(final String filteredBy, String query) {

        Specification<Contact> contactSpecification = (r, cq, cb) -> cb.like(r.get(filteredBy), "%" + query + "%");

        return contactSpecification;
    }
}
