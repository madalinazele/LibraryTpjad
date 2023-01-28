package com.library.core.repository.impl;

import com.library.core.model.VerificationToken;
import com.library.core.repository.VerificationTokenRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class DefaultVerificationTokenRepository extends DefaultAbstractRepository<VerificationToken, Integer> implements VerificationTokenRepository {

    public DefaultVerificationTokenRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<VerificationToken> findByValue(String value) {
        final String queryString = "FROM " + VerificationToken.class.getSimpleName() + " AS v WHERE v.value = :value";
        final Query<VerificationToken> query = getSession().createQuery(queryString, VerificationToken.class);
        query.setParameter("value", value);
        List<VerificationToken> tokens = query.getResultList();
        if (tokens.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(tokens.get(0));
    }

}
