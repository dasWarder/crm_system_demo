package com.example.service.specification;

import com.example.todoList.Task;
import com.example.exception.UnsupportedParameterException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskSpecification {

    public Specification<Task> findTasksByParams(final String[] filters, final String query) throws UnsupportedParameterException {

        List<String> unsupportedParams = findUnsupportedParams(filters);

        if(!unsupportedParams.isEmpty()) {

            throw new UnsupportedParameterException(
                    String.format("One or more parameters not available as filter params"));
        }

        Specification<Task> taskSpecification = (r, cq, cb) -> {

                List<Predicate> predicates = new ArrayList<>();

                for(String filter : filters) {
                    Predicate predicate = cb.like(r.get(filter), "%" + query + "%");
                    predicates.add(predicate);
                }

                Predicate resultPredicate = cb.or(predicates.toArray(
                                                                new Predicate[predicates.size()]));
                return resultPredicate;
            };

            return taskSpecification;
    }

    private List<String> findUnsupportedParams(String[] filters) {

        List<String> unsupportedParams = Arrays.stream(filters)
                .filter(f -> !f.equalsIgnoreCase("title") &&
                                !f.equalsIgnoreCase("description"))
                .collect(Collectors.toList());

        return unsupportedParams;
    }
}
