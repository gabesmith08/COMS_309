package _Server_Side.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBeans;

import _Server_Side.Server.Services.Activity_Service;
import _Server_Side.Server.Services.Group_Service;
import _Server_Side.Server.Services.User_Service;

import _Server_Side.Server.Entities.Activity_Entity;
import _Server_Side.Server.Entities.Group_Entity;
import _Server_Side.Server.Entities.User_Entity;

public class GroupTests {
	
	@Mock
	User_Entity user;
	
	@Mock
	Group_Entity group;
	
	@Mock
	Activity_Entity activity;

	@Mock
	User_Service userService;
	
	@Mock
	Group_Service groupService;
	
	@Mock
	Activity_Service activityService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}


//	//@Test
//	//public void getGroupByIdTest() {
//		
//		when(groupService.getGroup(1)).thenReturn(new Group_Entity(1, null, null, "test", "test bio"));
//		
//		group = groupService.getGroup(1);
//		
//		assertEquals(1,group.getId());
//		assertEquals(null,group.getUsers());
//		assertEquals(null,group.getUserIds());
//		assertEquals("test",group.getName());
//		assertEquals("test bio",group.getBio());
//	}
	
//	@Test
//	public void setGroupName() {
//		
//		when(groupService.getGroup(1)).thenReturn(new Group_Entity(1, null, null, "test", "test bio"));
//		
//		group = groupService.getGroup(1);
//		
//		group.setName("anothaone");
//		
//		group.setBio("this is sick yo");
//	
//		assertEquals("anothaone",group.getName());
//		assertEquals("this is sick yo",group.getBio());
//	}
	
//	@Test
//	public void addUserToGroup() {
//		
//		when(groupService.getGroup(1)).thenReturn(new Group_Entity(1, new HashSet(),  new HashSet(), "test", "test bio"));
//		
//		group = groupService.getGroup(1);
//		
//		User_Entity tempUser = new User_Entity();
//		
//		tempUser.setId(1);
//		
//		group.addUser(tempUser);
//		
//		HashSet<User_Entity> userSet = new HashSet();
//		
//		userSet.add(tempUser);
//	
//		assertEquals(userSet,group.getUsers());
//	}

}
