package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TripRepository;
import repositories.WarningNoteRepository;
import domain.Application;
import domain.Audit;
import domain.Explorer;
import domain.Finder;
import domain.Manager;
import domain.Note;
import domain.Sponsorship;
import domain.Stage;
import domain.Story;
import domain.SurvivalClass;
import domain.TagValue;
import domain.Trip;
import domain.WarningNote;

@Service
@Transactional
public class WarningNoteService {

	// Managed Repository
	
		@Autowired
		private WarningNoteRepository	warningNoteRepository;
		
		@Autowired
		private TripService		tripService;

		// Supporting Services
		@Autowired
		private ManagerService		managerService;

		// Constructor

		public WarningNoteService() {
			super();
		}

		// Simple CRUD methods
	
		public Collection<WarningNote> findAll() {
			return this.warningNoteRepository.findAll();
		}

		public WarningNote create(int tripId) {
			final WarningNote created = new WarningNote();
			Manager manager;
			manager = this.managerService.findByPrincipal();
			Trip trip;
			trip = this.tripService.findOne(tripId);
			created.setTrip(trip);
			created.setManager(manager);
			created.setTicker(this.generateTicker());
			return created;
		}
		
		public WarningNote save(final WarningNote warningNote) {

			Assert.notNull(warningNote);
			WarningNote result;
			result = this.warningNoteRepository.saveAndFlush(warningNote);
			
			return result;

		}
		
		public void delete(final WarningNote warningNote) {

			Assert.notNull(this.warningNoteRepository.findOne(warningNote.getId()));
		
		
			Assert.isTrue(warningNote.getManager().equals(this.managerService.findByPrincipal()));
			this.warningNoteRepository.delete(warningNote);

		}

		// Other business methods

		//Generate an unic ticker
		public String generateTicker() {

			String ticker = "";
			// YYMMDD
			final Calendar date = Calendar.getInstance();
			ticker += date.get(Calendar.YEAR);
			ticker = ticker.substring(2, 4);
			ticker += date.get(Calendar.MONTH) + 1;
			if (date.get(Calendar.DATE) < 10)
				ticker += "0" + date.get(Calendar.DATE);
			else
				ticker += date.get(Calendar.DATE);

			ticker += "-";

			//WWWW
			final char[] chr = {
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
			};
			for (int i = 0; i <= 3; i++)
				ticker += chr[(int) (Math.random() * 26)];

			//Recursivo para ver si existe ya este ticker
			final WarningNote warningNoteWithSameTicker = this.findWarningNoteByTicker(ticker);
			if (warningNoteWithSameTicker == null)
				return ticker;
			else
				return this.generateTicker();
		}
		public WarningNote findOne(final int warningNoteId) {
			final WarningNote warningNote = this.warningNoteRepository.findOne(warningNoteId);
			return warningNote;
		}
		
		public WarningNote findWarningNoteByTicker(final String ticker) {
			Assert.notNull(ticker);
			return this.warningNoteRepository.findWarningNoteByTicker(ticker);
		}
		
		public Collection<WarningNote> findByManager(final int managerId) {
			final Collection<WarningNote> warningNote = this.warningNoteRepository.findByManager(managerId);
			return warningNote;
		}
		
		public Collection<WarningNote> findByTrip(final int tripId) {
			final Collection<WarningNote> warningNotes = this.warningNoteRepository.findByTrip(tripId);
			return warningNotes;
		}
		
}
