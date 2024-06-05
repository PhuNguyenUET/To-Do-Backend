package com.skyline.todo.controller;

import com.skyline.todo.DTO.TagDTO;
import com.skyline.todo.model.scheduledTask.Tag;
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
    public ResponseEntity<TagDTO> addNewTag(@RequestBody @Valid TagDTO tag) {
        TagDTO t = tagService.post(tag);

        return ResponseEntity.ok(t);
    }

    @PutMapping("/update")
    public ResponseEntity<TagDTO> updateTag(@RequestBody @Valid TagDTO tag, @RequestParam("id") int tagId) {
        TagDTO t = tagService.update(tag, tagId);

        return ResponseEntity.ok(t);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTag(@RequestParam("id") int tagId) {
        tagService.delete(tagId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TagDTO>> getAllTag(Authentication authentication) {
        List<TagDTO> tags = tagService.getAllTag(authentication);

        return ResponseEntity.ok(tags);
    }

    @GetMapping("/getDisplay")
    public ResponseEntity<List<TagDTO>> getDisplayTag(Authentication authentication) {
        List<TagDTO> tags = tagService.getDisplayTag(authentication);

        return ResponseEntity.ok(tags);
    }

    @GetMapping("/getAllByDate")
    public ResponseEntity<List<TagDTO>> getAllTagByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Authentication authentication
    ) {
        List<TagDTO> tags = tagService.getAllTagInDay(authentication, date);

        return ResponseEntity.ok(tags);
    }
}
