package hms_backend.controller;

import hms_backend.entity.enums.Available;
import hms_backend.entity.enums.Section;
import hms_backend.entity.enums.Shift;
import hms_backend.entity.enums.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/enums")
@RequiredArgsConstructor
public class EnumController {
    @GetMapping("/sections")
    public Section[] getSections() {
        return Section.values();
    }

    @GetMapping("/shifts")
    public Shift[] getShifts() {
        return Shift.values();
    }
    @GetMapping("/availability")
    public Available[] getAvailability() {
        return Available.values();
    }
    @GetMapping("/type")
    public Type[] getType() {
        return Type.values();
    }

}
