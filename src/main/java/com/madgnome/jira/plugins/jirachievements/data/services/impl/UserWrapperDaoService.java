package com.madgnome.jira.plugins.jirachievements.data.services.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.crowd.embedded.api.User;
import com.google.common.collect.ImmutableMap;
import com.madgnome.jira.plugins.jirachievements.data.ao.UserWrapper;
import com.madgnome.jira.plugins.jirachievements.data.services.IUserWrapperDaoService;

public class UserWrapperDaoService extends BaseDaoService<UserWrapper> implements IUserWrapperDaoService
{
  @Override
  protected Class<UserWrapper> getClazz()
  {
    return UserWrapper.class;
  }

  public UserWrapperDaoService(ActiveObjects ao)
  {
    super(ao);
  }

  @Override
  public UserWrapper create(User jiraUser)
  {
    UserWrapper userWrapper = null;
    if (get(jiraUser) == null)
    {
      userWrapper = ao.create(UserWrapper.class, ImmutableMap.<String, Object>of("JIRA_USER_NAME", jiraUser.getName()));
    }

    return userWrapper;
  }

  @Override
  public UserWrapper get(User jiraUser)
  {
    return get(jiraUser.getName());
  }

  @Override
  public UserWrapper get(String jiraUserName)
  {
    UserWrapper[] userWrappers = ao.find(UserWrapper.class, "JIRA_USER_NAME = ?", jiraUserName);
    return userWrappers.length > 0 ? userWrappers[0] : null;
  }
}
