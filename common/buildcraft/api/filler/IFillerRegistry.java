package common.buildcraft.api.filler;

import java.util.Set;

import common.buildcraft.api.gates.IAction;

public interface IFillerRegistry {

	public void addPattern(IFillerPattern pattern);

	public IFillerPattern getPattern(String patternName);

	public IFillerPattern getNextPattern(IFillerPattern currentPattern);

	public IFillerPattern getPreviousPattern(IFillerPattern currentPattern);
	
	public Set<? extends IAction> getActions();
}
