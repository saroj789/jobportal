package com.jobportal.review.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.company.Company;
import com.jobportal.company.CompanyService;
import com.jobportal.review.Review;
import com.jobportal.review.ReviewRepository;
import com.jobportal.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private CompanyService companyService;
	

	@Override
	public List<Review> getReviewsByCompanyID(Long companyId) {
		List<Review> reviews = this.reviewRepository.findByCompanyId(companyId);
		return reviews;
	}

	@Override
	public boolean addReview(Long companyId, Review review) {
		Company company = this.companyService.getCompanyById(companyId);
		if(company != null) {
			review.setCompany(company);
			this.reviewRepository.save(review);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Review getReviewsById(Long reviewId) {
		Review review= this.reviewRepository.findById(reviewId).orElse(null);
		return review;
	}
	
	@Override
	public Review getReview(Long companyId, Long reviewId) {
		List<Review> reviews = this.reviewRepository.findByCompanyId(companyId);
		Review review = reviews.stream()
				.filter(r -> r.getId().equals(reviewId))
				.findFirst()
				.orElse(null);
							
		return review;
	}


	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review newReview) {
		Company company = this.companyService.getCompanyById(companyId);
		if( company != null) {
			newReview.setCompany(company);
			newReview.setId(reviewId);
			this.reviewRepository.save(newReview);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
		Company company = this.companyService.getCompanyById(companyId);
		if( company != null && this.reviewRepository.existsById(reviewId)) {
			Review review = this.reviewRepository.findById(reviewId).orElse(null);
			
			//remove this review from companay referance //i dont think its neededas we have used CASCADE
			//company.getReviews().remove(review);
			//this.companyService.updateCompany(companyId, company);
			
			//delete review
			this.reviewRepository.deleteById(reviewId);
			return true;
		}
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
