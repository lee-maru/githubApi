package org.example;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GithubConnectionTest {

    public static final String GITOKEN = ""; //gitToken
    public static final String ID = ""; //gitId

    @Test
    public void testConnect() throws IOException {
        final GitHub gitHub = new GitHubBuilder().withPassword(ID,GITOKEN).build();
        try {
            gitHub.checkApiUrlValidity();
        }catch (Exception e){
            throw new IOException("Check your github gitToken & username");
        }
    }

    @Test
    public void testTakeIssueList() throws IOException {
        final GitHub gitHub = new GitHubBuilder().withPassword(ID,GITOKEN).build();
        List<GHIssue> issues = gitHub.getRepository("whiteship/live-study").getIssues(GHIssueState.ALL);
        // 현재 개설된 이슈는 총 18개 (closed 포함)
        assertEquals(18, issues.size());
    }

    @Test
    public void testTakeCommentList() throws IOException{
        final GitHub gitHub = new GitHubBuilder().withPassword(ID,GITOKEN).build();
        List<GHIssue> issues = gitHub.getRepository("whiteship/live-study").getIssues(GHIssueState.ALL);
        List<String> users = new ArrayList<>();
        for(GHIssue issue : issues){
            for(GHIssueComment comment : issue.getComments()){
                users.add(comment.getUser().getLogin());
            }
        }
        System.out.println(users.size());
    }
}