package controllers;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Application;
import domain.Manager;
import domain.Trip;
import domain.WarningNote;

import services.ApplicationService;
import services.ManagerService;
import services.TripService;
import services.WarningNoteService;

@Controller
@RequestMapping("/warningNote")
public class WarningNoteController extends AbstractController{
	
	//Services
		@Autowired
		private WarningNoteService	warningNoteService;

		@Autowired
		private TripService			tripService;

		@Autowired
		private ManagerService		managerService;

		public WarningNoteController() {
			super();
		}
		
		//List
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list(@RequestParam final int tripId, @RequestParam(required = false) final String message) {
			final ModelAndView result;

			Collection<WarningNote> warningNotes;

			warningNotes = this.warningNoteService.findByTrip(tripId);

			result = new ModelAndView("warningNote/list");
			result.addObject("warningNotes", warningNotes);
			result.addObject("message", message);
			result.addObject("requestURI", "warningNote/list.do?tripId=" + tripId);

			return result;
		}
}
