package iul.iscte.tsio.interfaces;

import iul.iscte.tsio.model.ProgramEntity;

import java.util.List;

public interface ProgramDAO {
	public ProgramEntity getProgramByTitle(String title);
	public boolean updateProgram(ProgramEntity userToUpdate);
	public boolean insertUser(ProgramEntity programToInsert);
	public boolean deleteProgram(ProgramEntity programToDelete);
	public List<ProgramEntity> getAllPrograms();
}
