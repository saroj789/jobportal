package com.jobportal.review;

import java.util.List;

public interface ReviewService {
	
	List<Review> getReviewsByCompanyID(Long companyId);
	
	boolean addReview(Long companyId, Review review);
	
	Review getReviewsById(Long reviewId);

	Review getReview(Long companyId, Long reviewId);

	boolean updateReview(Long companyId, Long reviewId, Review newReview);

	boolean deleteReview(Long companyId, Long reviewId);
	

}
