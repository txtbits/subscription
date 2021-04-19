package com.subscription.subscriptionservice.repository;

import com.subscription.subscriptionservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	Subscription findOneByEmail(String subscriptionEmail);

	Optional<Subscription> findById(String anyString);

	void deleteById(String anyString);
}
