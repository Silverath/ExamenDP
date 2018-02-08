package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class WarningNote extends DomainEntity{

	// Attributes
		private String	ticker;
		private String	title;
		private String	description;
		private Integer	gauge;
		private Date	moment;



		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		@Column(unique = true)
		public String getTicker() {
			return this.ticker;
		}
		public void setTicker(final String ticker) {
			this.ticker = ticker;
		}

		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getTitle() {
			return this.title;
		}
		public void setTitle(final String title) {
			this.title = title;
		}

		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getDescription() {
			return this.description;
		}
		public void setDescription(final String description) {
			this.description = description;
		}
		
		@Min(1)
		@Max(3)
		@NotNull
		public Integer getGauge() {
			return this.gauge;
		}
		public void setGauge(final Integer gauge) {
			this.gauge = gauge;
		}

		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
		@Future
		public Date getMoment() {
			return this.moment;
		}
		public void setMoment(final Date moment) {
			this.moment = moment;
		}


		// Relationships
		private Trip						trip;
		private Manager						manager;
		
		
		@NotNull
		@Valid
		@ManyToOne(optional = false)
		public Manager getManager() {
			return this.manager;
		}

		public void setManager(final Manager manager) {
			this.manager = manager;
		}
		
		@NotNull
		@Valid
		@ManyToOne(optional = false)
		public Trip getTrip() {
			return this.trip;
		}

		public void setTrip(final Trip trip) {
			this.trip = trip;
		}

}
