package com.example.transaction.repository.spec;

import com.example.transaction.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionSpecification {

    public Specification<Transaction> getSpecification(UUID accountId, String startDate, String endDate) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new LinkedList<>();

            Predicate senderId = criteriaBuilder.equal(root.get("senderAccountId"), accountId);
            Predicate receiverId = criteriaBuilder.equal(root.get("receiverAccountId"), accountId);

            predicates.add(criteriaBuilder.or(senderId,receiverId));

            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {

                LocalDateTime start = LocalDateTime.parse(startDate);
                LocalDateTime end = LocalDateTime.parse(endDate);

                Predicate createdDateFilter = criteriaBuilder.between(root.get("transactionDate"), start, end);
                predicates.add(criteriaBuilder.and(createdDateFilter));
            }

            query.orderBy(criteriaBuilder.desc(root.get("transactionDate")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
