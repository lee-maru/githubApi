package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class GithubConnection {
    public static final String GITOKEN = ""; //gitToken
    public static final String ID = ""; //gitId

    public static void main(String[] args) throws IOException {
        final String repoName = "whiteship/live-study"; //gitRepository name

        /*
        * @param userId
        * @param gitAccessToken
         */
        final GitHub gitHub = new GitHubBuilder().withPassword(ID,GITOKEN).build();

        // github connection check
        try {
            gitHub.checkApiUrlValidity();
        }catch (Exception e){
            throw new IOException("Check your github gitToken & username");
        }

        // 한개의 레파지토리에서 이슈사항들을 List 에 담고 이슈사항이 존재 하지않는다면, IndexOutOfBoundsException 처리
        List<GHIssue> issues = gitHub.getRepository("whiteship/live-study").getIssues(GHIssueState.ALL);
        if(issues.size() == 0){
            throw new IndexOutOfBoundsException("There is no repository's issue.");
        }

        List<String> usersName = new ArrayList<>();

        /*1개의 이슈에 커멘트들의 로그인 아이디를 전부 분석하여, userName string List 에 넣어준다.
         * issues {
         *     issue_1 {
         *         comments{
         *             comment_1{
         *                  List<String> userName <- add.comment.userId
         *              }
         *             comment_2{
         *                  List<String> userName <- add.comment.userId
         *              }
         *    issue_2 {
         *         comments{
         *             comment_1{
         *                  List<String> userName <- add.comment.userId
         *              }
         *             comment_2{
         *                  List<String> userName <- add.comment.userId
         *              }
         */

        for(GHIssue issue : issues){
            List<String> checkDuplicateList = new ArrayList<>();
            for(GHIssueComment comment : issue.getComments()){
                usersName.add(comment.getUser().getLogin());
            }
        }

        HashMap<String, Integer> results = new HashMap<String, Integer>();

        for (String name : usersName) {
            if (results.containsKey(name)) { // if there is duplicate name
                results.put(name, results.get(name) + 1);
            } else { // if there is not name
                results.put(name, 1);
            }
        }
        
        for(Map.Entry<String, Integer> entry : results.entrySet()){
            //Math.round(percent*100)/100.0 소수점 2자리 계산
            double percent = Math.round(entry.getValue()/(double)issues.size()*100.0*100.0)/100.0;
            System.out.println("User : '"+ entry.getKey() +"' issueProgress : '" + percent +"%'");
        }
    }
}
