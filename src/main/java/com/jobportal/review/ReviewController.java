package com.jobportal.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getReviewsByCompanyId(@PathVariable Long companyId) {
		List<Review> reviews = this.reviewService.getReviewsByCompanyID(companyId);
		if(reviews.size()!=0) {
			return new ResponseEntity<>(reviews,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(reviews,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/reviews")
	private ResponseEntity<String> addReview(@PathVariable Long companyId,
			@RequestBody Review review) {
		boolean isAdded = this.reviewService.addReview(companyId, review);
		if(isAdded) {
			return new ResponseEntity<String>("Review Added Successfully",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Company not found with id "+companyId,HttpStatus.NOT_FOUND);
			
		}
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long companyId,
			@PathVariable Long reviewId) {
		//Review review = this.reviewService.getReviewsById(reviewId);
		Review review = this.reviewService.getReview(companyId, reviewId);
		if(review != null) {
			return new ResponseEntity<>(review,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(review,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId,
			@PathVariable Long reviewId,
			@RequestBody Review review) {
		boolean isUpdated = this.reviewService.updateReview(companyId, reviewId, review);
		if(isUpdated) {
			return new ResponseEntity<String>("Review Updated Successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Company not found with id "+companyId,HttpStatus.NOT_FOUND);
			
		}
	}
	
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
			@PathVariable Long reviewId)
	{
		boolean isDeleted = this.reviewService.deleteReview(companyId, reviewId);
		if(isDeleted) {
			return new ResponseEntity<String>("Review Deleted Successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Company or Review not found",HttpStatus.NOT_FOUND);
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
