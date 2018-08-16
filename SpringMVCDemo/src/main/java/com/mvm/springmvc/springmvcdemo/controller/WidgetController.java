package com.mvm.springmvc.springmvcdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mvm.springmvc.springmvcdemo.model.Widget;
import com.mvm.springmvc.springmvcdemo.repository.WidgetRepository;

@Controller
public class WidgetController {
	@Autowired
	private WidgetRepository widgetRepository;

	/**
	 * Load the new widget page.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/widget/new")
	public String newWidget(Model model) {
		model.addAttribute("widget", new Widget());
		return "widgetform";
	}

	/**
	 * Create a new widget.
	 * 
	 * @param widget
	 * @param model
	 * @return
	 */
	@PostMapping("/widget")
	public String createWidget(Widget widget, Model model) {
		widgetRepository.save(widget);
		return "redirect:/widget/" + widget.getId();
	}

	/**
	 * Get a widget by ID.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/widget/{id}")
	public String getWidgetById(@PathVariable Long id, Model model) {
		model.addAttribute("widget",
				widgetRepository.findById(id).orElse(new Widget()));
		return "widget";
	}

	/**
	 * Get all widgets.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/widgets")
	public String getWidgets(Model model) {
		model.addAttribute("widgets", widgetRepository.findAll());
		return "widgets";
	}

	@GetMapping("/handicap")
	public String getHandicap(Model model) {
		System.out.println("Here");
		int handicap = 0;
		int size = 0;
		Iterable<Widget> col = widgetRepository.findAll();
		for (Widget w : col) {
			handicap += w.getScore();
			size++;
		}
		handicap = handicap / size;
		model.addAttribute("handicap", handicap);
		return "Handicap";
	}

	/**
	 * Load the edit widget page for the widget with the specified ID.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/widget/edit/{id}")
	public String editWidget(@PathVariable Long id, Model model) {
		model.addAttribute("widget",
				widgetRepository.findById(id).orElse(new Widget()));
		return "widgetform";
	}

	/**
	 * Update a widget.
	 * 
	 * @param widget
	 * @return
	 */
	@PostMapping("/widget/{id}")
	public String updateWidget(Widget widget) {
		widgetRepository.save(widget);
		return "redirect:/widget/" + widget.getId();
	}

	/**
	 * Delete a widget by ID.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/widget/delete/{id}")
	public String deleteWidget(@PathVariable Long id) {
		widgetRepository.deleteById(id);
		return "redirect:/widgets";
	}
}