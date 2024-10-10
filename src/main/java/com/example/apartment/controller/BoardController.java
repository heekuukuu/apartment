package com.example.apartment.controller;

import com.example.apartment.model.Board;
import com.example.apartment.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"", "/"})
   public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.getPostList(pageable));
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";}

    @GetMapping("/board/{id}")
    public String getPost(Model model, @PathVariable int id){
        model.addAttribute("boards", boardService.getPost(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(Model model, @PathVariable int id){
        model.addAttribute("boards", boardService.getPost(id));
        return "board/updateForm";
    }
    @GetMapping("/board/search")
    public String searchPosts(@RequestParam("keyword") String keyword, Pageable pageable, Model model) {
        Page<Board> boards = boardService.searchPosts(keyword, pageable);
        model.addAttribute("boards", boards);
        model.addAttribute("totalCount", boards.getTotalElements());
        model.addAttribute("keyword", keyword); // JSP에서 검색어를 출력하기 위함
        return "index";  // 검색 결과를 보여줄 JSP 페이지
    }
}