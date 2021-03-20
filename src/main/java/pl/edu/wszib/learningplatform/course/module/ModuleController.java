package pl.edu.wszib.learningplatform.course.module;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/modules")
@Slf4j
@Tag(name = "Module Tag", description = "Rest endpoints for modules.")
public class ModuleController {

}
