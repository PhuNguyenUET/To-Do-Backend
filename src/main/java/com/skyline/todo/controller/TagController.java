package com.skyline.todo.controller;

import com.skyline.todo.model.sampleTask.Tag;
import com.skyline.todo.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping("/add")
    public ResponseEntity<Tag> addNewTag(@RequestBody @Valid Tag tag) {
        Tag t = tagService.post(tag);

        return ResponseEntity.ok(t);
    }

    @PutMapping("/update")
    public ResponseEntity<Tag> updateTag(@RequestBody @Valid Tag tag, @RequestParam("id") int tagId) {
        Tag t = tagService.update(tag, tagId);

        return ResponseEntity.ok(t);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTag(@RequestParam("id") int tagId) {
        tagService.delete(tagId);

        return ResponseEntity.ok("Tag deleted successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Tag>> getAllTag(Authentication authentication) {
        List<Tag> tags = tagService.getAllTag(authentication);

        return ResponseEntity.ok(tags);
    }

    @GetMapping("/getAllByDate")
    public ResponseEntity<List<Tag>> getAllTagByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Authentication authentication
    ) {
        List<Tag> tags = tagService.getAllTagInDay(authentication, date);

        return ResponseEntity.ok(tags);
    }
}
