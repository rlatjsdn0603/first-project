package com.been.beenbackend.Mapper;

import com.been.beenbackend.dto.Comment;
import com.been.beenbackend.dto.LikeComment;

import java.util.List;

public interface CommentMapper {
    List<Comment> list();

    int register(Comment comment);

    void modify(Comment comment);

    void delete(int commentId);

    Comment listOne(int commentId);

    List<Comment> listByPostId(int postId);

    int makeLike(int commentId, int userId);

    int deleteLike(int commentId, int userId);

    int showLikeyCnt(int commentId);

    List<LikeComment> getLikey(int postId, int userId);

}
