package org.campusincident.webservice.category;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.campusincident.webservice.CampusincidentApplication;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = CampusincidentApplication.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository repoCategory;
	
    @Autowired
    @InjectMocks
    CategoryService categoryService;
    
    @Before
    public void setup() {
		Mockito.when(repoCategory.save(any())).thenAnswer(i -> {
			System.out.println(i);
			return i;
		});
    }
 
	@Test
	public void testGetOrCreateString_mustCreateANewCategoryWhenNotExistingWithGivenName() {
		String wantedName = "wanted";
		Mockito.when(repoCategory.findById(wantedName)).thenReturn(Optional.empty());
		Category res = categoryService.getOrCreate(wantedName); 
		assertNotNull(res);
		assertTrue("wanted".equals(res.getName()));
		verify(repoCategory, times(1)).save(Mockito.any(Category.class));
	}
	
	@Test
	void testGetOrCreateString_musReturnTheCategoryWhenExisting() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOrCreateListOfString() {
		fail("Not yet implemented");
	}

	@Test
	void testRename() {
		fail("Not yet implemented");
	}

}
