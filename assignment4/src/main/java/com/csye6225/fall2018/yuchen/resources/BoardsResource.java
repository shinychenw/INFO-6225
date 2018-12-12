package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Board;
import com.csye6225.fall2018.yuchen.datamodel.Course;
import com.csye6225.fall2018.yuchen.service.BoardsService;
import com.csye6225.fall2018.yuchen.service.CoursesService;
import com.csye6225.fall2018.yuchen.service.SNSService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//../webapi/myresource
@Path("boards")
public class BoardsResource {

    BoardsService boardsService = new BoardsService();
    SNSService snsService = new SNSService();
    CoursesService coursesService = new CoursesService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Board> getBoardsByDepartment(@QueryParam("courseId") String courseId){
        if(courseId == null){
            return boardsService.getAllBoards();
        }
        return boardsService.getBoardByCourseId(courseId);
    }

    @GET
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Board getBoard(@PathParam("boardId") String boardId){
        return boardsService.getBoard(boardId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Board addBoard(Board board){
        String topicarn = snsService.createTopic(board.getBoardId());
        Course course = coursesService.getCourse(board.getCourseId());
        course.setNotificationTopic(topicarn);
        course.setBoardId(board.getBoardId());
        coursesService.updateCourseInformation(course.getCourseId(),course);
        return  boardsService.addBoard(board);
    }

    @DELETE
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Board deleteBoard(@PathParam("boardId") String boardId){
        return boardsService.deleteBoard(boardId);
    }

    @PUT
    @Path("/{boardId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Board updateBoard(@PathParam("boardId") String boardId, Board board){
        return boardsService.updateBoardInformation(boardId, board);
    }
}
