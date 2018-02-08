package controllers.manager;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import services.TripService;
import services.WarningNoteService;

import domain.Application;
import domain.Audit;
import domain.Auditor;
import domain.Explorer;
import domain.Manager;
import domain.Trip;
import domain.WarningNote;

@Controller
@RequestMapping("/warningNote/manager")
public class WarningNoteManagerController {

	//Services
			@Autowired
			private WarningNoteService	warningNoteService;

			@Autowired
			private TripService			tripService;

			@Autowired
			private ManagerService		managerService;

			public WarningNoteManagerController() {
				super();
			}
	
	//List
			@RequestMapping(value = "/list", method = RequestMethod.GET)
			public ModelAndView list(@RequestParam(required = false) final String message) {
				final ModelAndView result;
				
				Manager principal;
				principal = this.managerService.findByPrincipal();
				Collection<WarningNote> warningNotes;
				warningNotes = this.warningNoteService.findByManager(principal.getId());

				result = new ModelAndView("warningNote/list");
				result.addObject("warningNotes", warningNotes);
				result.addObject("message", message);
				result.addObject("requestURI", "/warningNote/manager/list.do");

				return result;
			}
			
			// Creation
			@RequestMapping(value = "/create", method = RequestMethod.GET)
			public ModelAndView create(@RequestParam final int tripId) {
				final ModelAndView result;
				WarningNote warningNote;

				warningNote = this.warningNoteService.create(tripId);
				result = this.createEditModelAndView(warningNote);

				return result;
			}
			
			@RequestMapping(value = "/edit", method = RequestMethod.GET)
			public ModelAndView edit(@RequestParam final int warningNoteId) {
				final ModelAndView result;
				WarningNote warningNote;
				final Manager principal = this.managerService.findByPrincipal();
				warningNote = this.warningNoteService.findOne(warningNoteId);

				Assert.isTrue(warningNote.getManager().equals(principal));

				Assert.notNull(warningNote);

				result = this.createEditModelAndView(warningNote);
				result.addObject(warningNoteId);

				return result;
			}

			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid final WarningNote warningNote, final BindingResult bindingResult) {
				ModelAndView result;

				if (bindingResult.hasErrors())
					result = this.createEditModelAndView(warningNote);
				else
					try {
						this.warningNoteService.save(warningNote);
						result = new ModelAndView("redirect:list.do");
					} catch (final Throwable oops) {
						String messageError = "application.commit.error";
						if (oops.getMessage().contains("message.error"))
							messageError = oops.getMessage();
						result = this.createEditModelAndView(warningNote, messageError);
					}

				return result;
			}


			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
			public ModelAndView delete(final WarningNote warningNote, final BindingResult binding) {
				ModelAndView result;

				final Manager principal = this.managerService.findByPrincipal();
				Assert.isTrue(warningNote.getId()!=0);
				Assert.isTrue(warningNote.getManager().equals(principal));

				try {

					this.warningNoteService.delete(warningNote);
					result = new ModelAndView("redirect:list.do");
				} catch (final Throwable oops) {
					String messageError = "warningNote.commit.error";
					if (oops.getMessage().contains("message.error"))
						messageError = oops.getMessage();
					result = this.createEditModelAndView(warningNote, messageError);
				}

				return result;
			}

			// Ancillary methods
			protected ModelAndView createEditModelAndView(final WarningNote warningNote) {
				ModelAndView result;

				result = this.createEditModelAndView(warningNote, null);

				return result;
			}
			protected ModelAndView createEditModelAndView(final WarningNote warningNote, final String message) {
				ModelAndView result;


				result = new ModelAndView("warningNote/edit");
				result.addObject("warningNote", warningNote);
				result.addObject("message", message);
				result.addObject("requestURI", "warningNote/manager/edit.do");

				return result;
			}
}
