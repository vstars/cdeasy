import jenkins.model.*;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;
import javaposse.jobdsl.plugin.*;

project = Jenkins.instance.createProject(FreeStyleProject, "seed")

project.getBuildersList().clear()
project.getBuildersList().add(new Shell("docker run -v \$PWD:/groovy -w /groovy camiloribeiro/gradle:1.10 build"));

project.getBuildersList().add(new ExecuteDslScripts(
  new ExecuteDslScripts.ScriptLocation("false","dsl/**/*.groovy",null),
  false,
  RemovedJobAction.DELETE,
  RemovedViewAction.DELETE,
  LookupStrategy.JENKINS_ROOT,
  "src/main/groovy")
);

project.save()
